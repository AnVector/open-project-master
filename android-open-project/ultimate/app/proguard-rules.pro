# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\adt-bundle-windows-x86_64-20140321\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:

#保留选项
#-----------------------------1.实体类-----------------------------------------------

#实现Serializable接口的JavaBean
-keep class com.looper.ultimate.bean.** { *; }

#-----------------------------2.第三方包---------------------------------------------

#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.** { *; }

#retrofit2
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-dontwarn org.robovm.**
-keep class org.robovm.** { *; }

#rxjava
-dontwarn rx.**
-keep class rx.** { *; }

-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
 long producerIndex;
 long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#umeng
-dontwarn com.umeng.**
-keep class com.umeng.**{*;}
-keep class u.aly.**{*;}
-keep class com.google.**{*;}

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

#-keepclasseswithmembernames class * {
#    @butterknife.* ;
#}

#nineoldandroids
-dontwarn com.nineoldandroids.*
-keep class com.nineoldandroids.** { *;}

#---------------------------------3.默认保留区---------------------------------
# 保持以下类不被混淆
-keep interface android.support.v4.app.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}
-keep public class * extends android.os.IInterface

-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
#    public (android.content.Context);
#    public (android.content.Context, android.util.AttributeSet);
#    public (android.content.Context, android.util.AttributeSet, int);
}

# 保持自定义控件类不被混淆
#-keepclasseswithmembers class * {
#    public (android.content.Context, android.util.AttributeSet);
#    public (android.content.Context, android.util.AttributeSet, int);
#}
#保持 Serializable 不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#不混淆资源类
-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}

# 保持 native 方法不被混淆
-keepclasseswithmembernames class * {
 native <methods>;
}

# 保持自定义控件类不被混淆
-keepclassmembers class * extends android.app.Activity {
 public void *(android.view.View);
}

# 保持枚举 enum 类不被混淆
-keepclassmembers enum * {
 public static **[] values();
 public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable { # 保持 Parcelable 不被混淆
 public static final android.os.Parcelable$Creator *;
}
#---------------------------------webview------------------------------------
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}


#---------------------------------4.基本指令区----------------------------------
#指定代码的压缩级别
-optimizationpasses 5
#不去忽略非公共的库类
-dontskipnonpubliclibraryclassmembers
#混淆前后的映射
-printmapping mapping.txt
#混淆时所采用的算法
-optimizations !code/simplification/cast,!field/*,!class/merging/*
#保护注解
-keepattributes *Annotation*,InnerClasses
#保护签名
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable

#如果有警告也不终止
-dontwarn
#包名不混合大小写
-dontusemixedcaseclassnames
#预校验
-dontpreverify
#混淆时是否记录日志
-verbose
#忽略警告
-ignorewarning
#apk 包内所有 class 的内部结构
-dump class_files.txt
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
-printusage unused.txt
#----------------------------------------------------------------------------
#--------------------------proguard语法解释--------------------------------------------------
#-include {filename}    从给定的文件中读取配置参数
#-basedirectory {directoryname}    指定基础目录为以后相对的档案名称
#-injars {class_path}    指定要处理的应用程序jar,war,ear和目录
#-outjars {class_path}    指定处理完后要输出的jar,war,ear和目录的名称
#-libraryjars {classpath}    指定要处理的应用程序jar,war,ear和目录所需要的程序库文件
#-dontskipnonpubliclibraryclasses    指定不去忽略非公共的库类。
#-dontskipnonpubliclibraryclassmembers    指定不去忽略包可见的库类的成员。
#
#保留选项
#-keep {Modifier} {class_specification}    保护指定的类文件和类的成员
#-keepclassmembers {modifier} {class_specification}    保护指定类的成员，如果此类受到保护他们会保护的更好
#-keepclasseswithmembers {class_specification}    保护指定的类和类的成员，但条件是所有指定的类和类成员是要存在。
#-keepnames {class_specification}    保护指定的类和类的成员的名称（如果他们不会压缩步骤中删除）
#-keepclassmembernames {class_specification}    保护指定的类的成员的名称（如果他们不会压缩步骤中删除）
#-keepclasseswithmembernames {class_specification}    保护指定的类和类的成员的名称，如果所有指定的类成员出席（在压缩步骤之后）
#-printseeds {filename}    列出类和类的成员-keep选项的清单，标准输出到给定的文件
#
#压缩
#-dontshrink    不压缩输入的类文件
#-printusage {filename}
#-dontwarn   如果有警告也不终止
#-whyareyoukeeping {class_specification}
#
#优化
#-dontoptimize    不优化输入的类文件
#-assumenosideeffects {class_specification}    优化时假设指定的方法，没有任何副作用
#-allowaccessmodification    优化时允许访问并修改有修饰符的类和类的成员
#
#混淆
#-dontobfuscate    不混淆输入的类文件
#-printmapping {filename}
#-applymapping {filename}    重用映射增加混淆
#-obfuscationdictionary {filename}    使用给定文件中的关键字作为要混淆方法的名称
#-overloadaggressively    混淆时应用侵入式重载
#-useuniqueclassmembernames    确定统一的混淆类的成员名称来增加混淆
#-flattenpackagehierarchy {package_name}    重新包装所有重命名的包并放在给定的单一包中
#-repackageclass {package_name}    重新包装所有重命名的类文件中放在给定的单一包中
#-dontusemixedcaseclassnames    混淆时不会产生形形色色的类名
#-keepattributes {attribute_name,...}    保护给定的可选属性，例如LineNumberTable, LocalVariableTable, SourceFile, Deprecated, Synthetic, Signature, and
#
#InnerClasses.
#-renamesourcefileattribute {string}    设置源文件中给定的字符串常量
