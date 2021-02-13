package com.example.uploadimg.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.uploadimg.model.User;
import com.example.uploadimg.database.dao.UserDao;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class DatabaseHandler extends RoomDatabase {

    private static DatabaseHandler databaseInstance;

    public abstract UserDao userDao();

    public static synchronized DatabaseHandler getInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseHandler.class, "user_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return databaseInstance;
    }

    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(databaseInstance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private final UserDao userDao;

        public PopulateDbAsyncTask(DatabaseHandler handler) {
            userDao = handler.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.insert(new User("ajay", "ajay@mail.com", "password", "9876543210", "state", "city"));
            return null;
        }
    }
}
