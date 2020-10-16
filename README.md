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
package com.example

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
    ...
    android:name="com.example.App"
    ...
/>
```
# Usage
Just call 
```
BlurSelect.selectView(context, selectView, viewForCard, config)
```
Where:
 - context - Application/Activity context
 - selectView - view what you want to focus
 - viewForCard - layout which you want to show after select (you can specify size as you want, or it will be calculated automatically)
 - config - ```BlurConfig``` object for determining animation durations and values, viewForCard size and other (there are some prepared configs for different animations)
 
 # Algorithm of logic and animations
 1) Duplicate select view
  - this step is needed for making select view on the top of screen. After original select view is duplicated - runs step 3)
 2) Animate duplicate of select view
  - scale down. After scale down runs step 4)
  - scale up
 3) Blur background and animate it
 4) Show card and animate it
 
 # Concept of animations
 
 The main animations is:
 1) scale down
 2) scale up
 
 Scale down can be turned off (watch Config properties #select view)
 
 When starting scale up, also can be run shadow and radius animation (watch Config properties #select view)
 
 # Prepared configs
 
 ## 1) BlurConfigs.withShadowConfig(elevationFrom, elevationTo)
 - elevationFrom - start shadow animation with this elevation
 - elevationTo - end shadow animation with this elevation
 
 Enables shadow animation.
 If you are using CardView as select view and you already have elevation, just specify 
 ```
 elevationFrom = CardView.cardElevation
 ```
 ![](with_shadow_config.gif)
 
 ## 2) BlurConfig.withRadiusConfig(radiusFrom, radiusTo)
 - radiusFrom - start animation with this radius
 - radiusTo - end animation with this radius
 ![](with_radius_config.gif)

 ## 3) BlurConfig.onlyScaleUpConfig(scaleUpTo)
 - scaleUpTo - end scale up animation with this scale (t can be lesser than 1)
 ![](only_scale_up_onfig.gif)
