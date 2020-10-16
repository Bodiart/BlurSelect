# BlurSelect - is a library for selecting view and blur all background
![](blur_select.gif)

# Setup
## 1) App level ```build.gradle```
```
dependencies {
    implementation 'com.github.Bodiart:BlurSelect:1.4.0'
}
```
## 2) Application class
```
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        BlurSelect.init(this)
    }
}
```
Dont forget to add this class in manifest (like this):
```
<application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme"
        android:name="com.example.App">
```
# Usage
Just call 
```
BlurSelect.selectView(context, selectView, viewForCard, config)
```
Where:
 - context is Application/Activity context
 - selectView is a view what you want to focus
 - viewForCard is a layout which you want to show after select (you can specify size as you want, or it will be calculated automatically)
 - config is BlurConfig object for determining animation durations and values, viewForCard size and other (there are some prepared configs for different animations)
