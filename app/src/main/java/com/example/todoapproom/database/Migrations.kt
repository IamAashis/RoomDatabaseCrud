package com.example.todoapproom.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

// version 2 -> 3 : added the "atitle" column to todo_table.
// This is what shipped databases at version 3 actually contain, so it has to
// stay as-is even though the column is dropped again in 3 -> 4.
val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE todo_table ADD COLUMN atitle TEXT")
    }
}

// version 3 -> 4 : dropped "atitle", added "createdAt" (Date stored as Long).
// SQLite before 3.35 (below API 34) has no DROP COLUMN, so the table is
// recreated and the rows are copied over.
val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS `todo_table_new` (" +
                "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "`title` TEXT, " +
                "`createdAt` INTEGER)"
        )
        // existing rows have no creation time, so createdAt stays null
        db.execSQL("INSERT INTO `todo_table_new` (`id`, `title`) SELECT `id`, `title` FROM `todo_table`")
        db.execSQL("DROP TABLE `todo_table`")
        db.execSQL("ALTER TABLE `todo_table_new` RENAME TO `todo_table`")
    }
}
