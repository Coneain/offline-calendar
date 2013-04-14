/**
 *  Copyright (C) 2013  Dominik Schürmann <dominik@dominikschuermann.de>
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

package org.sufficientlysecure.localcalendar.util;

import org.sufficientlysecure.localcalendar.CalendarMapper;

import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

public class AccountHelper {

    Context mContext;

    public AccountHelper(Context context) {
        mContext = context;
    }

    /**
     * Add account for Birthday Adapter to Android system
     * 
     * @param context
     * @return
     */
    public Bundle addAccount() {
        Log.d(Constants.TAG, "Adding account...");

        AccountManager am = AccountManager.get(mContext);
        if (am.addAccountExplicitly(CalendarMapper.ACCOUNT, null, null)) {

            // explicitly disable sync
            ContentResolver.setSyncAutomatically(CalendarMapper.ACCOUNT,
                    CalendarMapper.CONTENT_AUTHORITY, false);
            ContentResolver.setIsSyncable(CalendarMapper.ACCOUNT, AccountManager.KEY_ACCOUNT_TYPE,
                    0);

            Bundle result = new Bundle();
            result.putString(AccountManager.KEY_ACCOUNT_NAME, CalendarMapper.ACCOUNT.name);
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, CalendarMapper.ACCOUNT.type);
            return result;
        } else {
            return null;
        }
    }

}
