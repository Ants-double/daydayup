# 
- resultType (自动封装)
- 如果返回一个集合，就写集合中元素的类型
- 返回一个map  直接写map，mybatis已经起好了别名
- 如果map指定主键，还是写value的元素，key通过@MapKey()方法注解来指定主键
- resultMap （自定义封装 与resultType 二选一）
```
<resultMap id="myEmp" type="com.antsdouble.bean.Employee">
        <!--id 主键 property 那个属性 id会优化-->
        <id column="id" property=""></id>
        <result column="last_name" property="lastName"></result>
    </resultMap>
```  

- 可以用级联属性来查对像中的对象 osgl表达式支持的
```
  <resultMap id="myDifEmp" type="com.antsdouble.bean.Employee">
        <id column="id" property="id"></id>
        <result column="last_name" property="lastName"></result>
        <result column="gender" property="gender"></result>
        <result column="did" property="department.id"></result>
        <result column="dept_name" property="department.departmentName"></result>

    </resultMap>
```
- association 来实现级联查询
```
<resultMap id="myDifEmp" type="com.antsdouble.bean.Employee">
        <id column="id" property="id"></id>
        <result column="last_name" property="lastName"></result>
        <result column="gender" property="gender"></result>
        <!--<result column="did" property="department.id"></result>-->
        <!--<result column="dept_name" property="department.departmentName"></result>-->
        <!--
        javaType 级联的javabean引用
        -->
        <association property="department" javaType="com.antsdouble.bean.Department">
            <id column="did" property="id"/>
            <result column="dept_name" property="departmentName"/>
        </association>

    </resultMap>
```  
- association 进行分步查询
```
<resultMap id="myEmpByStep" type="com.antsdouble.bean.Employee">
        <id column="id" property="id"></id>
        <result column="last_name" property="lastName"/>
        <result column="gender" property="gender"/>
        <!--column传参-->
        <association property="department" select="com.antsdouble.dao.DepartmentMapper.getDeptById" column="d_id">

        </association>
    </resultMap>
    <select id="getEmpByIdStep" resultMap="myEmpByStep">
        select  e.id id ,e.last_name last_name,e.gender gender,e.d_id d_id  from tb1_employee e where id =#{id}
    </select>
    //另一个查询是已存在的
    <resultMap id="myDept" type="com.antsdouble.bean.Department">
        <id column="id" property="id"/>
        <result column="dept_name" property="departmentName"/>
    </resultMap>
    <select id="getDeptById" resultMap="myDept">
        select id,dept_name  from tb1_dept where  id =#{id}
    </select>
```  
- collection 定义关联和集合属性（一对多的关系）
- property  外层的对象属性
- ofType javaBean
- select 也可以实现分步查询 
- column 传多列时封装成map{key1=column1,key2=column2}
- lazy 开启延迟加载
- 
discriminator 判断 某列的值 改变封装行为
column 判断 的列值 javaType 对应的java类型
case value resultType 

