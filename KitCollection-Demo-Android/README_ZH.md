# 全局收藏服务示例代码（Android）

[![Apache-2.0](https://img.shields.io/badge/license-Apache-blue)](http://www.apache.org/licenses/LICENSE-2.0)
[![Open Source Love](https://img.shields.io/static/v1?label=Open%20Source&message=%E2%9D%A4%EF%B8%8F&color=green)]()
[![Java Language](https://img.shields.io/badge/language-java-green.svg)](https://www.java.com/en/)

[English](README.md) | 中文

## 目录

- [全局收藏服务示例代码（Android）](#collection kit示例代码android)
  - [目录](#目录)
  - [介绍](#介绍)
  - [环境要求](#环境要求)
  - [硬件要求](#硬件要求)
  - [开发准备](#开发准备)
  - [安装](#安装)
  - [技术支持](#技术支持)
  - [许可证](#许可证)

## 介绍

在这个示例代码中，你将使用创建的演示项目来了解全局收藏服务 。通过演示项目，你将：

1. 学习如何在你的应用中接收并处理来自全局收藏服务的数据

更多信息，请参考：[业务简介](https://developer.honor.com/cn/docs/11035/guides/introduction)。

## 环境要求

推荐使用 Android targetSdkVersion 30 或更高版本，以及 JDK 1.8 或更高版本。

## 硬件要求

一台运行 Windows 10 或 Windows 7 的电脑（台式机或笔记本）
一部带有 USB 数据线的荣耀手机，用于调试

## 开发准备

1. 注册荣耀帐号，成为荣耀开发者。
2. 创建应用，启动接口。
3. 构建本示例代码，需要先把它导入安卓集成开发环境（Android Studio的版本为2021.2.1及以上）。然后从[荣耀开发者服务平台](https://developer.honor.com/)下载应用的mcs-services.json文件，并添加到对应示例代码根目录下。另外，需要生成签名证书指纹并将证书文件添加到项目中，然后将配置添加到build.gradle。详细信息，请参见[集成指南](https://developer.honor.com/cn/docs/11032/guides/intergrate)集成准备。

## 安装

方法 1：在 Android Studio 中编译并构建 APK。然后，将 APK 安装到手机上并进行调试。
方法 2：在 Android Studio 中生成 APK。使用 Android 调试桥（ADB）工具运行 **adb install {*app/build/outputs/apk/debug/app-debug.apk*}** 命令将 APK 安装到手机上并进行调试。

## 技术支持

如果你对示例代码有任何疑问，请尝试以下方法：

- 访问 [荣耀开发者论坛](https://developer.hihonor.com/cn/forum/?navation=dh11614886576872095748%2F1) ，获取关于Collection Kit的最新讯息，并与其他开发者交流见解。
- 如果您在尝试示例代码中遇到问题，请向仓库提交[issue](https://github.com/Honor-Developer/CollectionKit-demo/issues)，也欢迎您提交[Pull Request](https://github.com/Honor-Developer/CollectionKit-demo/pulls)。
- 访问 [Stack Overflow](https://stackoverflow.com/questions/tagged/honor-developer-services?tab=Votes)，提交你的问题，在`honor-developer-services`标签下提问，有荣耀研发专家在线一对一解决您的问题。

## 许可证

示例代码根据 [Apache 许可证 2.0](http://www.apache.org/licenses/LICENSE-2.0) 许可。