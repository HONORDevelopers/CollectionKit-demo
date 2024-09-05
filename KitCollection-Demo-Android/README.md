# Collection Kit Sample Code (Android)

[![Apache-2.0](https://img.shields.io/badge/license-Apache-blue)](http://www.apache.org/licenses/LICENSE-2.0)
[![Open Source Love](https://img.shields.io/static/v1?label=Open%20Source&message=%E2%9D%A4%EF%B8%8F&color=green)]()
[![Java Language](https://img.shields.io/badge/language-java-green.svg)](https://www.java.com/en/)

English | [中文](README_ZH.md)

## Contents

- [collection Kit Sample Code (Android)](#collection-kit-sample-code-android)
  - [Contents](#contents)
  - [Introduction](#introduction)
  - [Environment Requirements](#environment-requirements)
  - [Hardware Requirements](#hardware-requirements)
  - [Preparations](#preparations)
  - [Installation](#installation)
  - [Technical Support](#technical-support)
  - [License](#license)

## Introduction

In this sample code, you'll use the demo project you created to learn about the Global Collections service. Through the demo project, you will:

1. Learn how to receive and process data from the Global Collections service in your app

For more information, see 
[Service Introduction](https://developer.honor.com/cn/docs/11035/guides/introduction).

## Environment Requirements

Android targetSdkVersion 30 or later and JDK 1.8 or later are recommended.

## Hardware Requirements

A computer (desktop or laptop) running Windows 10 or Windows 7
A Honor phone with a USB data cable, which is used for debugging

## Preparations

1.	Register as a Honor developer.
2.	Create an app and start APIs.
3.	Import your demo project to Android Studio (Chipmunk | 2021.2.1) or later. Download the **mcs-services.json** file of the app from [Honor Developer Site](https://developer.honor.com/), and add the file to the root directory of your project. Generate a signing certificate fingerprint, add the certificate file to your project, and add the configuration to the *build.gradle* file. For details, please refer to the [integration preparations](https://developer.honor.com/cn/docs/11032/guides/intergrate).


## Installation

Method 1: Compile and build the APK in Android Studio. Then, install the APK on your phone and debug it.
Method 2: Generate the APK in Android Studio. Use the Android Debug Bridge (ADB) tool to run the **adb install {*app/build/outputs/apk/debug/app-debug.apk*}** command to install the APK on your phone and debug it.

## Technical Support

If you have any questions about the sample code, try the following:

- Visit the [Honor Developer Forum](https://developer.hihonor.com/cn/forum/?navation=dh11614886576872095748%2F1)  to get the latest information about Collection Kit and exchange insights with other developers.
- If you have trouble trying the sample code, please submit an [issue](https://github.com/Honor-Developer/CollectionKit-demo/issues) to the repository, and you are also welcome to submit a [Pull Request](https://github.com/Honor-Developer/CollectionKit-demo/pulls)。
- Visit [Stack Overflow](https://stackoverflow.com/questions/tagged/honor-developer-services?tab=Votes), submit your questions, ask them under the 'honor-developer-services' tab, and have an HONOR R&D expert solve your problem one-on-one online.

## License

The sample code is licensed under [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).