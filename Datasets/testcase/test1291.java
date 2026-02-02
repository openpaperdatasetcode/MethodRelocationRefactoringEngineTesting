package test.refactor.movemethod;
import java.util.ArrayList;
/**
Target generic class (default modifier, static nested class as target_feature)
@param <T> Generic type parameter*/class TargetClass<T> {public T targetField; // Target field (per_condition: source contains target field)
public TargetClass(T targetField) {this.targetField = targetField;}
// Static nested class (target_feature)public static class TargetStaticNestedClass {
private U nestedField;
public TargetStaticNestedClass(U nestedField) {this.nestedField = nestedField;}
public U getNestedField() {return nestedField;}}
public T getTargetField() {return targetField;}}
/**
Source generic class (public, same package, static nested class + member inner class as features)
@param <S> Generic type parameter for source
@param <T> Generic type parameter matching target*/public class SourceClass<S, T> {private S sourceField;
public SourceClass(S sourceField) {this.sourceField = sourceField;}
// Static nested class (source_class feature)public static class SourceStaticNestedClass<V> {private V staticNestedField;
public SourceStaticNestedClass(V staticNestedField) {this.staticNestedField = staticNestedField;}
public V getStaticNestedField() {return staticNestedField;}}
// Member inner class (source_class feature) - method_position: source_innerpublic class SourceMemberInnerClass {private int innerField = 1; // For TypeDeclarationStatement target_feature "1"
// Instance method to be refactored (public, return TargetClass Type, target class: target)public TargetClass<T> methodToRefactor(TargetClass<T> target) {// TypeDeclarationStatement (private, this.field, 1, pos: source)private class InnerTypeDeclaration {public void printData() {System.out.println(this.getClass().getSimpleName() + ": " + SourceMemberInnerClass.this.innerField); // this.field (inner class field), 1}}InnerTypeDeclaration typeDecl = new InnerTypeDeclaration();typeDecl.printData();
// Constructor invocation (target class constructor)TargetClass.TargetStaticNestedClass<T> targetNested = new TargetClass.TargetStaticNestedClass<>(target.getTargetField());
// Do statementArrayList<T> doList = new ArrayList<>();int doCount = 0;do {doList.add(target.getTargetField());doCount++;} while (doCount < 2);
// While statementArrayList<T> whileList = new ArrayList<>();int whileCount = 0;while (whileCount < 2) {whileList.add(target.getTargetField());whileCount++;}
// Variable call (target field + source field + nested class field)T targetFieldCall = target.targetField; // Target field (per_condition)S sourceFieldCall = SourceClass.this.sourceField;V staticNestedCall = (V) new SourceStaticNestedClass<>("nestedData").getStaticNestedField();System.out.println("Variable calls: " + targetFieldCall + ", " + sourceFieldCall + ", " + staticNestedCall);
// Raw typeArrayList rawTypeList = new ArrayList(); // Raw type (no generic parameter)rawTypeList.add(targetFieldCall);
// Throw statement (no_new_exception: unchecked exception)if (targetFieldCall == null) {throw new IllegalArgumentException("Target field cannot be null");}
// Return statement (return TargetClass Type)return new TargetClass<>(targetFieldCall);}}
// Factory method to create member inner class instancepublic SourceMemberInnerClass createInnerInstance() {return new SourceMemberInnerClass();}}
// Test class for refactoring verificationpublic class MoveMethodRefactorTest_5212 {public static void main(String[] args) {// Initialize source and target instancesSourceClass<String, Integer> source = new SourceClass<>("sourceData");SourceClass.SourceMemberInnerClass inner = source.createInnerInstance();TargetClass<Integer> target = new TargetClass<>(100);
// Execute refactored methodTargetClass<Integer> result = inner.methodToRefactor(target);System.out.println("Refactored method result: " + result.getTargetField());}}
