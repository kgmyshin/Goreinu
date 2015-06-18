[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Goreinu-green.svg?style=flat)](https://android-arsenal.com/details/1/2000)

# Goreinu

copy Application Directory files to internal sdcard.(`/data/data/package_name/... -> /sdcard/Goreinu/package_name`).

## Getting started

In your `build.gradle` :

```
 dependencies {
   debugCompile 'kgmyshin:goreinu:0.0.10'
   releaseCompile 'kgmyshin:goreinu-no-op:0.0.10'
 }
```

 In your `Application` class:

 ```
 public class ExampleApplication extends Application {
     @Override
     public void onCreate() {
        super.onCreate();
        Goreinu.install(this);
     }
 }
 ```

## How to Run copy

There are two ways.

- click this icon in launcher. (In the your release variant, This icon is never shown.)

![](https://raw.githubusercontent.com/kgmyshin/Goreinu/master/art/launcher.png)

- send intent from adb.

```
adb shell am  broadcast -a com.kgmyshin.goreinu.intent
```

## License

```
Copyright 2015 kgmyshin

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
