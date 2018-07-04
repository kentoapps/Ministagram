🚧️ This app is still WIP 🚧️

# About
This is Instagram like application which is not for release or monetize.

⚠️ I don't upload `google-services.json` (for Firebase) for security reason. It means you cannot use this app even if you clone and run it but I'll deploy the app to somewhere soon.

# Why I created this app
I created this app to show my developing skill.  
I can write clean codes in Kotlin with good architecture which makes the app sustainable and testable.

I can also make beautiful layout using ConstraintLayout. I used to use RelativeLayout and LinearLayout before but ConstrainLayout is better to make efficient layout.

Instagram looks simple but it has so many features.  
Obviously it requires internet connection and showing posts in list furthermore uploading image file with caption. 
They are basic features of app but you can also add more features such as uploading videos, adding filter to images if you want.  
That's why I imagined this is good theme to investigate some technologies which I've never done.

# UI Design
<img width="814" alt="sketch_design" src="https://user-images.githubusercontent.com/8979200/42127900-2419e0da-7c55-11e8-889c-e21607a0c7aa.png">

I created ui design using Sketch.  
Since this app's objective is showing my skills of development, UI was not highest priority so I imitated Instagram some parts but I cared fundamental principles of Material Design.  
I arranged ui design during developing so the app differs a little from the design.

# Language and Libraries
- Kotlin
- Android Support Libraries
- RxJava 2
- Dagger 2
- Firebase (Auth, Firestore, Storage)
- Picasso
- PermissionDispatcher
- Timber
- Mockito

Kotlin is modern language supported by Google as official language of Android. It's expressive, concise, and powerful. I bet that Kotlin will be standard language of Android.

In order to work with architecture, I'm using RxJava and Dagger. They enables you to write clean and readable codes.

I also wrote test codes using Mockito but I wanted to put a priority on developing features so I wrote just few test cases this time. In practical, writing test first is better though.

# Architecture
<img width="1009" alt="screen shot 2018-07-02 at 22 19 06" src="https://user-images.githubusercontent.com/8979200/42199953-07a873b0-7e46-11e8-8728-87c47aa9a36a.png">

## UI Layer
UI layer architecture is MVVM using ViewModel and Data Binding from Jetpack.  
ViewModel classes have some datas which is shown on View. ViewModel classes also have logics and it's not depended on other classes such as Activity, Fragment and View. Therefore it's easy to test them.  
Thanks to Data Binding, I can bind data which is in ViewModel and layout(xml) without writing redundant biding codes!

## DataLayer
In data layer there are Repository and DataSource. Both of them has interface therefore it's easy to replace module and test. 

DataSource is responsble to get data from somewhere. For instance, `RemoteDataStore` connects to API to get data from server and `LocalDataStore` connects to local database to get cached data. They implement same DataSource interface so it's easy to be replaced.  
Repository has logics switching `RemoteDataStore` and `LocalDataStore`.  
I'll implement `LocalDataStore` using Room.

# Android Jetpack
![jetpack_donut](https://user-images.githubusercontent.com/8979200/42127909-3e1d901c-7c55-11e8-8eb0-713632607778.png)

In Google I/O 2018, Google announced Android Jetpack which is a set of libraries, tools and architectural guidance to help make it quick and easy to build great Android apps.  
Some of them are still alpha or beta but I love trying cutting-edged features and this is a good opportunity to do it so I tried some of them!

I wrote down what I usded through developing this app.

## Architecture
- Data Binding
- Lifecycles
- LiveData
- Navigation
- Paging (Upcoming!)
- Room (Upcoming!)
- ViewModel
- WorkManager (Upcoming!)

Data Binding, LiveData and ViewModel are great combination to manage Lifecycles.  
If you use Data Binding, you can bind view and data without writing boilerplate.  
Also LiveData and ViewModel care data state so it's safe even if user rotates screen.

Navigation is one of exciting new features for me! It's still alpha and there aren't enough information or tips about it but it's powerful so it'll be standard feature in the near future.  
Navigation looks like Storyboard iOS which shows screen navigation clearly. However it differs from Storyboard. You cannot edit views in layout but edit only navigation including transition animation and arguments.  
I like Navigation because I can write more simple codes in class file and pass arguments to another fragment safely(type safe).

## Foundation
- AppCompat
- Android KTX
- Test

## UI
- Fragment
- Layout

I used basic elements of UI component.  
ConstraintLayout enables you to make flat and efficient layout.  
I'd also like to use MotionLayout which is new feature of ConstraintLayout 2.0 eventually!

## Behavior
- Permissions
- Notifications (Upcoming!)

After API level 23, runtime permission is required.  
The procedure is a little bit complicated and redundant so I'm using a library PermissionDispatcher.  
Thanks to the library I can implement runtime permission clearly using annotations.

# CI and Deployment
I'll use CI for automated unit tests and deployment!

# Upcoming Features
- Notifications
- Filtering posts on timeline
- Cache posts using Room
- Uploading image background using WorkManager
- Paging timeline
- Refreshing timeline by pulling down
- Editing user info
- Like button
- Comment feature
- Following / Followed user
- Adding filter to image

# License
```
Copyright 2018 Kento Uchida

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