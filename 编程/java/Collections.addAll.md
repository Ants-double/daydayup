我们在编码时经常需要将一些元素添加到一个List中，此时我们一般有两种选择：Collections.addAll()或者是ArrayList.addAll()。
1. 在需添加元素比较少的情况下，并在List的size在万级以上时，一般建议Collections.addAll()，但当List的size较小时，两种方法没有什么区别，甚至ArrayList.addAll()更好。
2. 当我们将一个数组添加到一个List中时，Collections.addAll()和ArrayList.addAll()没有什么性能差异，但当我们将一个List添加到一个List中时，建议使用ArrayList.addAll()。
3. 添加数组和列表，要比添加元素快。
HashMap和HashSet有类似用法。

- 源码
``` java
/**
     * Adds all of the specified elements to the specified collection.
     * Elements to be added may be specified individually or as an array.
     * The behavior of this convenience method is identical to that of
     * <tt>c.addAll(Arrays.asList(elements))</tt>, but this method is likely
     * to run significantly faster under most implementations.
     *
     * <p>When elements are specified individually, this method provides a
     * convenient way to add a few elements to an existing collection:
     * <pre>
     *     Collections.addAll(flavors, "Peaches 'n Plutonium", "Rocky Racoon");
     * </pre>
     *
     * @param  <T> the class of the elements to add and of the collection
     * @param c the collection into which <tt>elements</tt> are to be inserted
     * @param elements the elements to insert into <tt>c</tt>
     * @return <tt>true</tt> if the collection changed as a result of the call
     * @throws UnsupportedOperationException if <tt>c</tt> does not support
     *         the <tt>add</tt> operation
     * @throws NullPointerException if <tt>elements</tt> contains one or more
     *         null values and <tt>c</tt> does not permit null elements, or
     *         if <tt>c</tt> or <tt>elements</tt> are <tt>null</tt>
     * @throws IllegalArgumentException if some property of a value in
     *         <tt>elements</tt> prevents it from being added to <tt>c</tt>
     * @see Collection#addAll(Collection)
     * @since 1.5
     */
    @SafeVarargs
    public static <T> boolean addAll(Collection<? super T> c, T... elements) {
        boolean result = false;
        for (T element : elements)
            result |= c.add(element);
        return result;
    }

```