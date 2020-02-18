# if

```
 <!--
    1.where 1=1 and 这是一种解决方案
    2.mybatis可以使用where 标签把查询包含在内,自动的将and 或or 去掉 前提上and 放在前面
    3.trim 就是拼接标签 prefix 前缀 prefixOverrides 前缀覆盖 suffix 后缀 suffixOverrides后缀覆盖
    -->
    <select id="getEmpByCondition" resultMap="myEmpCon">
        select id,last_name,gender,email from tb1_employee
        <!--<where>-->
            <!--<if test="id!=null">-->
                <!--id=#{id}-->
            <!--</if>-->
            <!--<if test="lastName!=null &amp;&amp; lastName!=&quot;&quot;">-->
                <!--and last_name like #{lastName}-->
            <!--</if>-->
            <!--<if test="email!=null and email.trim()!=&quot;&quot;">-->
                <!--and email=#{email}-->
            <!--</if>-->
            <!--<if test="gender==0 or gender ==1">-->
                <!--and gender=#{gender}-->
            <!--</if>-->

        <!--</where>-->

        <trim prefix="where"  suffixOverrides="and">
            <if test="id!=null">
            id=#{id} and
            </if>
            <if test="lastName!=null &amp;&amp; lastName!=&quot;&quot;">
             last_name like #{lastName}and
            </if>
            <if test="email!=null and email.trim()!=&quot;&quot;">
             email=#{email}and
            </if>
            <if test="gender==0 or gender ==1">
             gender=#{gender}
            </if>
        </trim>
    </select>
```  
# choose
```
 <!--getEmpByConditionChoose-->
    <select id="getEmpByConditionChoose" resultType="com.antsdouble.bean.Employee">
        select * from tb1_employee
        <where>
            <choose>
                <when test="id!=null">
                    id=#{id}
                </when>
                <when test="lastName!=null">
                    last_name=#{lastName}
                </when>
                <when test="gender!=null">
                    gender=#{gender}
                </when>
                <otherwise>
                    gender=0
                </otherwise>
            </choose>
        </where>
    </select>
```
# collection 将遍历出的元素指定给变量,list参数会做特殊片理，名子就是参数名list
```
   <!--
    separator 元素之间的分隔符
    -->
    <select id="getEmpByConditionForeach" resultType="com.antsdouble.bean.Employee">

        <foreach collection="list" item="item_id" separator="," open="select  * from tb1_employee WHERE id in (" close=")">
            #{item_id}
        </foreach>
    </select>
```  
# 内置参数
1. 不只是方法传进来的参数用来判断，mybatis还有
2. _parameter 整个参数（一个） 多个参数会封装一个map 代表的就是最后封装后的对象map
3. 和
4. _databaseId  如果配置了DatabaseIdProvider标签，那么这个就是当前数据库选中的值（别名）

# 可以用bind修改传入值的操作，比如拼接字符串等操作
# sql 标签抽取一些公用的表达式 可以用include refid 来进行引用

