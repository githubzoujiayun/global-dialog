global-dialog
======

A **global** dialog frame for Android. For another reading pattern see [the website][1].

![](http://upload-images.jianshu.io/upload_images/2109293-dec5ac150c17ff3e.gif?imageMogr2/auto-orient/strip)

Download
--------
Maven:
```xml
<dependency>
  <groupId>com.txm.topcodes.globaldialog</groupId>
  <artifactId>globaldialog</artifactId>
  <version>0.1.6</version>
  <type>pom</type>
</dependency>
```
or Gradle:
```groovy
compile 'com.txm.topcodes.globaldialog:globaldialog:0.1.6'
```

Usage mode
--------
Common：
```java
new GlobalDialog.Builder()
                .setContext(context) 
                .setDescription("hello world") //弹出框的提示信息。
                .setStyle(GlobalDialog.Style.SingleAlert) //弹窗的样式。有DoubleAlert、SingleAlert两种，默认为后者。
                .setForce(ture) //设置弹框销毁的策略。默认为true。
                .setDialogClickListener(new OnDialogClickListener() { //设置回调，也可以不设置。
                    @Override
                    public void onSure() {
                        super.onSure();
                    }
                }).build().show();
```
Or simple：
```java
new GlobalDialog.Builder()
                .setContext(context) 
                .setDescription("hello world") 
                .build().show();
```
Snapshots of the development version are available in [Tangxianming's `snapshots` repository][snap].
License
-------

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


 [1]: http://www.jianshu.com/p/d0f3e9705860
 [snap]: https://dl.bintray.com/kimball/maven/
