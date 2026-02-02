package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RecursiveMethodAnn {}
/**
Generic target class with static nested class
@param <T> Target class type parameter*/final class TargetClass<T> {protected T targetField;
public static class TargetStaticNested1 {}
public static class TargetInnerRec {public void targetInstanceMethod() {}}}
/**
Generic source class with static nested classes
@param <S> Source class type parameter*/public class SourceClass<S> {private TargetClass<String> targetField = new TargetClass<>();protected S outerProtectedField;
public static class SourceStaticNested1 {}
public static class SourceStaticNested2 {}
/**
Recursive method to process target class components
@param depth Recursion depth
@param nested Target inner recursive component*/@RecursiveMethodAnnpublic final void recursiveMethod(int depth, TargetClass.TargetInnerRec nested) {if (depth <= 0) {return;}
synchronized (this) {TargetClass.TargetStaticNested1 staticNested = new TargetClass.TargetStaticNested1();targetField.targetField = "processed_" + depth;nested.targetInstanceMethod();}
S localVar = outerProtectedField;recursiveMethod(depth - 1, nested);}}