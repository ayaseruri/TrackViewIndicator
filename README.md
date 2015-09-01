# TrackViewIndicator
![demo_gif](https://github.com/ayaseruri/TrackViewIndicator/blob/master/demo/demo.gif?raw=true)

[demo_apk](https://github.com/ayaseruri/TrackViewIndicator/tree/master/demo?raw=true)

# you can use this lib like:
```java
TrackPageIndicator trackPageIndicator = (TrackPageIndicator)findViewById(R.id.indicator);
trackPageIndicator.setViewPager(viewPager, new ArrayList<IGetSnapshot>());
```

IGetSnapshot is a interface:
```java
public interface IGetSnapshot{
        View getOrgSnapshot();  //return the original view.
        void getAftSnapshot(View orgView);  //the view that will change by swipe the viewpager
    }
```

Gradle
```java
compile 'andy.ayaseruri.trackpageindicator:lib:1.0.0'
```

Maven
```xml
<groupId>andy.ayaseruri.trackpageindicator</groupId>
<artifactId>lib</artifactId>
<version>1.0.0</version>
```

thanks to

[张鸿洋的博客](http://blog.csdn.net/lmj623565791/article/details/44098729)
