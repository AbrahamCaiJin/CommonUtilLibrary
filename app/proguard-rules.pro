# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\android-sdk-windows/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

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
#Glide
#-keep public class * implements com.bumptech.glide.module.GlideModule
#-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
#  **[] $VALUES;
#  public *;
#}

# for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule
#Picasso
#-dontwarn com.squareup.okhttp.**
#Fresco
# Keep our interfaces so they can be used by other ProGuard rules.
# See http://sourceforge.net/p/proguard/bugs/466/
#-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

# Do not strip any method/class that is annotated with @DoNotStrip
#-keep @com.facebook.common.internal.DoNotStrip class *
#-keepclassmembers class * {
#    @com.facebook.common.internal.DoNotStrip *;
#}

# Keep native methods
#-keepclassmembers class * {
#    native <methods>;
#}

#-dontwarn okio.**
#-dontwarn com.squareup.okhttp.**
#-dontwarn okhttp3.**
#-dontwarn javax.annotation.**
#-dontwarn com.android.volley.toolbox.**
#-dontwarn com.facebook.infer.**