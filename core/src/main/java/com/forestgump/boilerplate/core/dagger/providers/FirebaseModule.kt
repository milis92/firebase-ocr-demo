package com.forestgump.boilerplate.core.dagger.providers

import com.forestgump.boilerplate.core.dagger.scopes.ApplicationScope
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer
import dagger.Module
import dagger.Provides

@Module
internal class FirebaseModule {

    @ApplicationScope
    @Provides
    fun provideFirebaseDetector() : FirebaseVisionTextRecognizer =
            FirebaseVision.getInstance().onDeviceTextRecognizer

}