1 什么是UID
网络间的数据传输最终都是要转化为二进制流的方式进行传输，为了方便转换以及进行验证，我们应该把对角序列化，当实现Seriabizable接口时，UID就是一个必须的属性，可以方便进行版本验证。
2 IDEA 自动生成UID
- setting ->Editor->Inspections->serialization issues
- Serializable class without serivalVersionUID 后面打上对号
- 应用保存
- 然后在类上按ALT+ ENTER选择生成UID
![图片](https://www.cnblogs.com/images/cnblogs_com/ants_double/1413361/o_uid.jpg)