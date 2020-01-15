# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile

# Move everything to the root package
-repackageclasses

# Required for classes created and used from JNI code (on C/C++ side)
-keep, includedescriptorclasses class in.uncod.android.bypass.Document { *; }
-keep, includedescriptorclasses class in.uncod.android.bypass.Element { *; }

# Internal
-dontwarn com.android.org.conscrypt.SSLParametersImpl
-dontwarn dalvik.system.CloseGuard
-dontwarn kotlin.internal.**
-dontwarn kotlin.reflect.jvm.internal.ReflectionFactoryImpl
-dontwarn org.apache.harmony.xnet.provdier.jsse.SSLParametersImpl
-dontwarn org.conscrypt.**
-dontwarn sun.misc.Unsafe
-dontwarn sun.security.ssl.SSLContext.Impl