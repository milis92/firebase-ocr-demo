package com.forestgump.boilerplate.core.domain.repositories

import android.content.SharedPreferences
import com.forestgump.boilerplate.core.dagger.qualifiers.EncryptedKv
import com.forestgump.boilerplate.core.domain.Repository
import com.forestgump.boilerplate.core.domain.daos.NewsLocalService
import com.forestgump.boilerplate.core.domain.models.News
import com.forestgump.boilerplate.core.domain.services.NewsRemoteService
import io.reactivex.Completable
import io.reactivex.Single

interface NewsRepository : Repository {
    fun sync() : Completable
    fun getAll(): Single<News>
}

internal class NewsRepositoryImpl constructor(
        private val remoteService: NewsRemoteService,
        private val localService: NewsLocalService,
        @EncryptedKv private val encryptedKv: SharedPreferences
) : NewsRepository {
    override fun sync() : Completable{
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(): Single<News> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
