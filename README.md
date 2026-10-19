# Pixel3D

图片生成3D体素模型。(image to 3d model object or voxel.)


## 说明（about）：

该项目是很久很久前的图生模型小工具，因保存源程序和代码的硬件已损坏故此复刻。


### 项目进度：
- [x] vertices
- [x] cube
- [x] wavefront
- [ ] voxel


## 用法（Usage）：
* ### Pixel3D:

* #### read()

```java
read(File imageFileFront, File imageFileLeft, File imageFileUp){}
```

分别读取3个方向（前，左和上，至少有其中1个）对应的png，扫描非透明部分以获取数据。

* #### toObject()

```java
toObject(File modelFile){}
```

填入带路径的模型文件，则导出体素模型*.obj到文件。

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


## 许可（License）：

---

License :
 [Apache License (Version 2.0)](http://www.apache.org/licenses/)

---