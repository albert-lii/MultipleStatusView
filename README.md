# MultipleStatusView

![releasesvg] ![apisvg] [![license][licensesvg]][license]     

## 关于
一个可以用来切换多种状态视图的view，适用于加载失败状态、空数据状态、网络异常状态等场景，同时支持自定义视图！

## 演示
![demogif]

## 添加依赖
- 使用Gradle
```java
    dependencies {
         compile 'com.liyi.view:multiple-status-view:1.0.0'
    }
```
- 使用Maven
```java
   <dependency>
      <groupId>com.liyi.view</groupId>
      <artifactId>multiple-status-view</artifactId>
      <version>1.0.0</version>
      <type>pom</type>
   </dependency>
```

## 默认提供的类型
|  类型  |  说明  |
|:-------|:------:|
| MultipleStatusView.ViewType.TYPE_LOADING |  加载中  |
| MultipleStatusView.ViewType.TYPE_NETWORK_POOR |  网络异常  |
| MultipleStatusView.ViewType.TYPE_EMPTY |  空数据  |
| MultipleStatusView.ViewType.TYPE_ERROR |  加载失败  |
| MultipleStatusView.ViewType.TYPE_SPECIFIED |  自定义  | 

**除了默认提供的类型，也可自定义类型   

## 自定义属性
|  属性  |  说明  |
|:-------|:------:|
| loading |  加载中视图  |
| network_poor |  网络异常视图  |
| empty |  空数据视图  |
| error |  加载失败视图  |
| specified |  自定义视图  | 

##  使用方法
#### 代码中使用
```Java  
  // 显示指定类型的视图
  MultipleStatusView.showView(int viewType)
  
  // 动态设置视图
  MultipleStatusView.setView(int viewType，int layoutId)
  MultipleStatusView.setView(int viewType，View view)
  
  // 关闭状态视图
  MultipleStatusView.dismiss()
```

#### xml中使用
```Java  
  <com.liyi.multiple.MultipleStatusView
      android:id="@+id/multipleStatusView_custom"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      app:empty="@layout/custom_test_view"
      app:error="@layout/custom_test_view"
      app:loading="@layout/custom_test_view"
      app:network_poor="@layout/custom_test_view"
      app:specified="@layout/custom_test_view" />
```

## 赞赏
如果你感觉 `MultipleStatusView` 帮助到了你，可以点右上角 "Star" 支持一下 谢谢！:blush:

## LICENSE
Copyright 2017 liyi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


[releasesvg]:https://img.shields.io/badge/Release-1.0.0-brightgreen.svg
[apisvg]: https://img.shields.io/badge/API-9+-brightgreen.svg
[licensesvg]: https://img.shields.io/badge/License-Apache--2.0-brightgreen.svg
[license]:http://www.apache.org/licenses/LICENSE-2.0
[statussvg]:https://img.shields.io/librariesio/github/phoenixframework/phoenix.svg  

[demogif]:https://github.com/albert-lii/MultipleStatusView/blob/master/snapshot/demo.gif
