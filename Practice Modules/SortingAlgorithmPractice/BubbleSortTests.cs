// ReSharper disable all CheckNamespace
// Please ensure all the tests are contained within the same namespace


using NUnit.Framework;
using System;
using System.Collections.Generic;

[TestFixture]
internal class BubbleSortTests
{
    [Test]
    public void BasicBubbleSort_WithSortedArray_ReturnsSameArray()
    {
        int[] data = { 1, 2, 3, 4, 5 };
        BubbleSort.basicBubbleSort(data, data.Length);
        Assert.AreEqual(new int[] { 1, 2, 3, 4, 5 }, data);
    }

    [Test]
    public void BasicBubbleSort_WithReverseSortedArray_ReturnsSortedArray()
    {
        int[] data = { 5, 4, 3, 2, 1 };
        BubbleSort.basicBubbleSort(data, data.Length);
        Assert.AreEqual(new int[] { 1, 2, 3, 4, 5 }, data);
    }

    [Test]
    public void BasicBubbleSort_WithUnsortedArray_ReturnsSortedArray()
    {
        int[] data = { 4, 2, 5, 3, 1 };
        BubbleSort.basicBubbleSort(data, data.Length);
        Assert.AreEqual(new int[] { 1, 2, 3, 4, 5 }, data);
    }

    [Test]
    public void BasicBubbleSort_WithNullArray_ThrowsNullPointerException()
    {
        int[] data = null;
        Assert.Throws<NullPointerException>(() => BubbleSort.basicBubbleSort(data, 0));
    }

    [Test]
    public void BasicBubbleSort_WithEmptyArray_ThrowsIllegalArgumentException()
    {
        int[] data = { };
        Assert.Throws<IllegalArgumentException>(() => BubbleSort.basicBubbleSort(data, 0));
    }

    [Test]
    public void BasicBubbleSortComparator_WithCustomComparator_ReturnsSortedArray()
    {
        string[] data = { "pear", "apple", "orange" };
        BubbleSort.basicBubbleSort(data, data.Length, new ReverseStringComparator());
        Assert.AreEqual(new string[] { "pear", "orange", "apple" }, data);
    }
}

// Comparators and missing exception stubs
public class ReverseStringComparator : IComparer<string>
{
    public int Compare(string x, string y)
    {
        return string.Compare(y, x);
    }
}

public class NullPointerException : Exception
{
    public NullPointer