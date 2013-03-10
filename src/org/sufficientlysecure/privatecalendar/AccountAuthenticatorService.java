/**
 *  Copyright (C) 2012  Dominik Schürmann <dominik@dominikschuermann.de>
 *  Copyright (C) 2012  Harald Seltner <h.seltner@gmx.at>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.sufficientlysecure.privatecalendar;

import org.sufficientlysecure.privatecalendar.ui.MainActivity;
import org.sufficientlysecure.privatecalendar.util.Constants;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class AccountAuthenticatorService extends Service {
    private static AccountAuthenticatorImpl sAccountAuthenticator = null;

    public AccountAuthenticatorService() {
        super();
    }

    @Override
    public IBinder onBind(Intent intent) {
        IBinder ret = null;
        if (intent.getAction().equals(android.accounts.AccountManager.ACTION_AUTHENTICATOR_INTENT))
            ret = getAuthenticator().getIBinder();
        return ret;
    }

    private AccountAuthenticatorImpl getAuthenticator() {
        if (sAccountAuthenticator == null)
            sAccountAuthenticator = new AccountAuthenticatorImpl(this);
        return sAccountAuthenticator;
    }

    private static class AccountAuthenticatorImpl extends AbstractAccountAuthenticator {
        private Context mContext;

        public AccountAuthenticatorImpl(Context context) {
            super(context);
            mContext = context;
        }

        /*
         * (non-Javadoc)
         * 
         * @see android.accounts.AbstractAccountAuthenticator#addAccount(android.
         * accounts.AccountAuthenticatorResponse, java.lang.String, java.lang.String,
         * java.lang.String[], android.os.Bundle)
         */
        @Override
        public Bundle addAccount(AccountAuthenticatorResponse response, String accountType,
                String authTokenType, String[] requiredFeatures, Bundle options)
                throws NetworkErrorException {
            Bundle result = new Bundle();
            Intent i = new Intent(mContext, MainActivity.class);
            // i.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
            result.putParcelable(AccountManager.KEY_INTENT, i);
            return result;
        }

        /*
         * (non-Javadoc)
         * 
         * @see android.accounts.AbstractAccountAuthenticator#confirmCredentials(
         * android.accounts.AccountAuthenticatorResponse, android.accounts.Account,
         * android.os.Bundle)
         */
        @Override
        public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account,
                Bundle options) {
            Log.e(Constants.TAG, "confirmCredentials not implemented for Birthday Adapter!");
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see android.accounts.AbstractAccountAuthenticator#editProperties(android
         * .accounts.AccountAuthenticatorResponse, java.lang.String)
         */
        @Override
        public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
            Log.e(Constants.TAG, "editProperties not implemented for Birthday Adapter!");
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see android.accounts.AbstractAccountAuthenticator#getAuthToken(android
         * .accounts.AccountAuthenticatorResponse, android.accounts.Account, java.lang.String,
         * android.os.Bundle)
         */
        @Override
        public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account,
                String authTokenType, Bundle options) throws NetworkErrorException {
            Log.e(Constants.TAG, "getAuthToken not implemented for Birthday Adapter!");
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see android.accounts.AbstractAccountAuthenticator#getAuthTokenLabel(java .lang.String)
         */
        @Override
        public String getAuthTokenLabel(String authTokenType) {
            Log.e(Constants.TAG, "getAuthTokenLabel not implemented for Birthday Adapter!");
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see android.accounts.AbstractAccountAuthenticator#hasFeatures(android
         * .accounts.AccountAuthenticatorResponse, android.accounts.Account, java.lang.String[])
         */
        @Override
        public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account,
                String[] features) throws NetworkErrorException {
            Log.e(Constants.TAG, "hasFeatures: " + features);
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see android.accounts.AbstractAccountAuthenticator#updateCredentials(android
         * .accounts.AccountAuthenticatorResponse, android.accounts.Account, java.lang.String,
         * android.os.Bundle)
         */
        @Override
        public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account,
                String authTokenType, Bundle options) {
            Log.e(Constants.TAG, "updateCredentials not implemented for Birthday Adapter!");
            return null;
        }
    }
}
