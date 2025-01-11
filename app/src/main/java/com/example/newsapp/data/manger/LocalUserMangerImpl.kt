package com.example.newsapp.data.manger

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsapp.domain.manger.LocalUserManger
import com.example.newsapp.util.Constants
import com.example.newsapp.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// DataStore를 사용하여 앱의 사용자 데이터를 로컬에 저장하고 읽는 기능을 제공한다.

class LocalUserMangerImpl(
    private val context : Context
): LocalUserManger { // LocalUserManager 인터페이스를 구현

    // 앱 첫 진입 여부를 저장하는 함수
    // suspend 키워드가 붙어 있으므로 비동기로 실행 가능
    override suspend fun saveAppEntry() {
        // DataStore를 사용하여 데이터를 저장
        context.dataStore.edit {  settings ->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    // 앱의 첫 진입 여부를 읽는 함수
    // Flow<Boolean> 타입을 반환하여 데이터를 비동기로 관찰 가능
    override fun readAppEntry(): Flow<Boolean> {
        // DataStore에서 데이터를 읽어 Flow로 반환
        return context.dataStore.data.map { preferences ->
            // PreferencesKeys.App_Entry 키의 값을 가져오며, 값이 없으면 기본값(false)을 반환
            preferences[PreferencesKeys.APP_ENTRY] ?: false
        }
    }
}

// Context를 통해 DataStore 객체를 초기화하며, 이름은 "USER_SETTINGS"로 설정
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

// PreferencesKeys 객체는 DataStore에서 사용할 키를 정의
private object PreferencesKeys {
    // APP_ENTRY 키는 Constants.APP_ENTRY의 문자열 값을 기반으로 booleanPreferencesKey를 생성
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}