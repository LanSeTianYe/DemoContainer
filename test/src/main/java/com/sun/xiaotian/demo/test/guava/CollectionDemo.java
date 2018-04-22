package com.sun.xiaotian.demo.test.guava;

import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.google.common.primitives.ImmutableLongArray;
import com.google.common.primitives.Ints;
import org.apache.commons.collections4.multiset.HashMultiSet;

import java.util.*;

public class CollectionDemo {

    enum SexEnum {Mam, Woman}

    public static void main(String[] args) {
        testLists();
        testMaps();
        testImmutableCollections();
        testNewCollectionType();
        testCollectionUtils();
    }

    private static void testLists() {
        ArrayList<String> strings = Lists.newArrayList(Splitter.on(",").omitEmptyStrings().trimResults().split("a,b,c,d,e"));
        System.out.println(strings);

        List<List<String>> partition = Lists.partition(strings, 1);
        for (List<String> stringList : partition) {
            System.out.println(stringList);
        }
    }

    private static void testMaps() {
        Map<Object, Object> nameMap = Maps.newHashMap();
        nameMap.put("132", "12312");
        Map<SexEnum, Object> enumMap = Maps.newEnumMap(SexEnum.class);
        enumMap.put(SexEnum.Mam, "123");
        enumMap.put(SexEnum.Woman, "456");
        enumMap.put(SexEnum.Woman, "456");
        System.out.println(enumMap);
    }

    private static void testImmutableCollections() {
        ImmutableSet<String> immutableSet = ImmutableSet.of("a_set", "b_set", "b_set", "c_set");
        ImmutableSet<String> immutableSetCopy = ImmutableSet.copyOf(immutableSet);
        ImmutableSet<Object> immutableSetBuilder = ImmutableSet.builder().add(immutableSet).build();

        ImmutableList<String> immutableList = ImmutableList.of("a", "b", "c");
        ImmutableMap<String, String> immutableMap = ImmutableMap.of("key", "value");
        ImmutableLongArray longArray = ImmutableLongArray.of(1);

        ImmutableClassToInstanceMap<T> classToInstanceMap = ImmutableClassToInstanceMap.of(T.class, new T());
        System.out.println(immutableSet);
        System.out.println(immutableSetCopy);
        System.out.println(immutableSetBuilder);

        System.out.println(immutableList);
        System.out.println(immutableMap);
        System.out.println(longArray);
        System.out.println(classToInstanceMap);
    }


    /**
     * 测试新集合类
     */
    private static void testNewCollectionType() {
        HashMultiset<String> hashMultiset = HashMultiset.create();
        hashMultiset.add("a");
        hashMultiset.add("b");
        hashMultiset.add("b");
        hashMultiset.add("c");
        System.out.println("hashMultiset.count: " + hashMultiset.count("b"));

        Set<Multiset.Entry<String>> entrySet = hashMultiset.entrySet();
        Iterator<Multiset.Entry<String>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Multiset.Entry<String> next = iterator.next();
            System.out.println(next.getElement() + " : " + next.getCount());
        }

        SetMultimap<String, Integer> setMultimap = MultimapBuilder.treeKeys().hashSetValues().build();
        setMultimap.put("a", 1);
        setMultimap.put("a", 2);
        setMultimap.put("a", 3);
        setMultimap.get("a").add(4);
        setMultimap.get("a").add(5);

        setMultimap.put("b", 1);
        setMultimap.put("b", 2);
        setMultimap.put("b", 3);

        System.out.println("setMultimap.get: " + setMultimap.get("a"));
        Set<Map.Entry<String, Integer>> entries = setMultimap.entries();
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("setMultimap.keySet().size(): " + setMultimap.keySet().size());

        HashBiMap<String, Integer> hashBiMap = HashBiMap.create();
        hashBiMap.put("a", 1);
        // IllegalArgumentException: value already present: 1
//        hashBiMap.put("b", 1);
        hashBiMap.put("c", 2);

        BiMap<Integer, String> inverse = hashBiMap.inverse();
        System.out.println("hashBiMap.keySet(): " + hashBiMap.keySet());

        HashBasedTable<Integer, Integer, Integer> hashBasedTable = HashBasedTable.create();
        hashBasedTable.put(1, 1, 1);
        hashBasedTable.put(1, 2, 2);
        hashBasedTable.put(1, 3, 1);

        hashBasedTable.put(2, 1, 2);
        hashBasedTable.put(2, 2, 4);

        System.out.println("hashBasedTable.row(1): " + hashBasedTable.row(1));

        MutableClassToInstanceMap<String> classToInstanceMap = MutableClassToInstanceMap.create();
        classToInstanceMap.put(String.class, "a");
        classToInstanceMap.put(String.class, "b");

        System.out.println("classToInstanceMap: " + classToInstanceMap);

        TreeRangeSet<Comparable<?>> treeRangeSet = TreeRangeSet.create();
        treeRangeSet.add(Range.closed(3, 7));
        treeRangeSet.add(Range.closedOpen(8, 9));
        System.out.println(treeRangeSet);
    }

    private static void testCollectionUtils() {
        ArrayList<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            tempList.add(i);
        }

        Iterator<Integer> iterator = tempList.iterator();
        System.out.println("Iterators.limit: " + Iterators.limit(iterator, 10));
        System.out.println("Iterators.partition: " + Iterators.partition(iterator, 10));
        System.out.println("Iterators.getLast: " + Iterators.getLast(iterator));
        System.out.println("Iterators.getNext: " + Iterators.getNext(iterator, 0));

        System.out.println(Lists.partition(tempList, 10));
        System.out.println(Lists.reverse(tempList));
        System.out.println(Lists.newArrayList(1,2,3,5));

        Set<String> wordsWithPrimeLength = ImmutableSet.of("one", "two", "three", "six", "seven", "eight");
        Set<String> primes = ImmutableSet.of("two", "three", "five", "seven");

        Sets.SetView<String> intersection = Sets.intersection(primes, wordsWithPrimeLength);
        System.out.println(intersection.immutableCopy());
    }

    static class T {}
}


