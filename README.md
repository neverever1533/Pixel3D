# Pixel3D

图片生成3D体素模型。(image to 3d model object or voxel.)

图示：

![image](https://github.com/neverever1533/Pixel3D/blob/main/p3d_x2.png)


## 说明（about）：

利用3视图还原模型的外形轮廓，扫描并生成体素方块结构的3D模型。


### 项目进度：
- [x] vertices
- [x] cube
- [x] wavefront
- [ ] voxel


## 用法（Usage）：
* ### Pixel3D:

* #### setRightMode()

```java
setRightMode(boolean isRightMode){}
```

为省去左右视图的翻转另存步骤，setRightMode(true)，read()导入的是右视图对应的png文件。

* #### read()

```java
read(File imageFileFront, File imageFileLeft, File imageFileUp){}
```

分别读取3个方向（即3视图：前，左和上，至少有其中1个）对应的png，扫描非透明部分以获取数据。

* #### toObject()

```java
toObject(File modelFile){}
```

* #### toFullObject()

```java
toFullObject(File modelFile){}
```

填入带路径的模型文件，则导出模型*.obj到文件（默认精简内部线面，仅保留最外层部分。toFullObject()得到的是未精简内部结构的体素模型）。

---

使用程序：

```java
String imgPathFront = "f.png";
String imgPathLeft = "l.png";
String imgPathUp = "u.png";
String objPath = "m.obj";

File imageFileFront = new File(imgPathFront);
File imageFileLeft = new File(imgPathLeft);
File imageFileFront = new File(imgPathUp);
File modelFile = new File(objPath);

Pixel3D pixel3D = new Pixel3D();
pixel3D.read(imageFileFront, imageFileLeft, imageFileUp);
pixel3D.toObject(modelFile);
```


## 其他

该项目是本人很久很久前做的图生模型工具，因保存源程序和代码的硬件已损坏故此重制。


## 许可（License）：

---

License :
 [Apache License (Version 2.0)](http://www.apache.org/licenses/)

---