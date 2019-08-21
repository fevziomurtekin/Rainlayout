# Rainlayout

Constraintlayout based, rain animated layout's


<br>

[![](https://jitpack.io/v/fevziomurtekin/BeforeAfterView.svg)](https://jitpack.io/#fevziomurtekin/BeforeAfterView)

# Demo

<p align="center">
<img src="https://media.giphy.com/media/UqqwiBQS31268sI9UU/giphy.gif" width="270"  height="480" />
</p>

# Setup
## Gradle
```Gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
  
  .....

dependencies {
      implementation 'com.github.fevziomurtekin:Rainlayout:1.0.0'
   }
}
```


> Warning : To Stop the animation in Activity / Fragment changes!

```kotlin
    override fun onStop() {
        super.onStop()
        rainview.animationClear()
    }
```




## Layout

```xml
  <com.fevziomurtekin.widget.RainlayoutView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/rainview"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:background="@android:color/holo_orange_light"
        xmlns:android="http://schemas.android.com/apk/res/android"
        app:isColorful="true"
        app:dropPerSecond="1"
        app:durationOfDropTime="500"
        app:dropSrc="@drawable/umbrella"
        app:dropTintColor="@color/colorPrimary">


</com.fevziomurtekin.widget.RainlayoutView>

```

 # Attributes

  | Attribute | Description |
| --- | --- |
| `isColorful` | This attribute makes the drop colorful. You can choose true or false (by default false) | 
| `dropPerSecond` | This attribute determines how many drops per second. You can value the data type Int. (by default 100) |
| `durationOfDropTime` | This attribute determines the number of seconds the drop will drop to the floor. You can value the data type Int-milisecond.(by default 500)|
| `dropSrc` | This attribute change the view of the drop.|
| `dropTintColor` | The attribute change the color of the drop (by default @android:color/white)|

## License
The Apache License 2.0 - see [`LICENSE`](LICENSE) for more details
