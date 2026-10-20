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

为省去使用图形软件翻转左右视图，重新存储为png的步骤，替代为使用setRightMode(true)。（默认为左视图，填入true则对应着read(front, right, up)。）


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


### 注意：
1. 对应3视图的3张png边缘尺寸需要一致，且其中的图形需要保持水平和垂直居中。（参考立方体互相垂直的三个面，如左侧面矩形的上边和顶面矩形的左边要一样长，立方体的其他面的边长以此类推。）
2. 手绘像素图使用效果最佳，若是通过图形软件缩小后的png，需手动清理遭破坏的半透明轮廓！
3. 测试128x128像素尺寸的png图片填入read(image, image, image)后，使用toObject(file)和toFullObject(file)获取*.obj的用时较快，当尺寸超过256x256像素以后结果受硬件性能影响。（不建议使用256像素或更大的尺寸。）

 
## 其他（Other）：

该项目是本人很久很久前做的图生模型工具，因保存源程序和代码的硬件已损坏故此重制。


## 许可（License）：

---

License :
 [Apache License (Version 2.0)](http://www.apache.org/licenses/)

---