package com.example.uploadimg.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.uploadimg.database.DatabaseHandler;
import com.example.uploadimg.database.dao.UserDao;
import com.example.uploadimg.model.User;
import com.example.uploadimg.utility.AsyncResponse;

public class UserRepository {

    private final UserDao userDao;

    public UserRepository(Application application) {
        DatabaseHandler handler = DatabaseHandler.getInstance(application);
        userDao = handler.userDao();
    }

    public void getUser(String username, AsyncResponse asyncResponse) {
        new GetUserAsyncTask(asyncResponse, userDao).execute(username);
    }

    public void getDuplicate(String emailID, String PhoneNo, AsyncResponse asyncResponse) {
        new GetDuplicateAsyncTask(asyncResponse, userDao).execute(emailID, PhoneNo);
    }

    public void insert(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    private static class GetUserAsyncTask extends AsyncTask<String, Void, User> {

        public AsyncResponse asyncResponse;
        private final UserDao userDao;

        public GetUserAsyncTask(AsyncResponse asyncResponse, UserDao userDao) {
            this.asyncResponse = asyncResponse;
            this.userDao = userDao;
        }

        @Override
        protected User doInBackground(String... strings) {
            return userDao.get(strings[0]);
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            asyncResponse.onAsyncProcessFinish(user);
        }
    }

    private static class GetDuplicateAsyncTask extends AsyncTask<String, Void, User> {

        public AsyncResponse asyncResponse;
        private final UserDao userDao;

        public GetDuplicateAsyncTask(AsyncResponse asyncResponse, UserDao userDao) {
            this.asyncResponse = asyncResponse;
            this.userDao = userDao;
        }

        @Override
        protected User doInBackground(String... strings) {
            return userDao.getDuplicate(strings[0], strings[1]);
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            asyncResponse.onAsyncProcessFinish(user);
        }
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {

        private final UserDao userDao;

        public InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }
}
