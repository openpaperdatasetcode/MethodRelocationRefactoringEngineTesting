package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnnotation {}
class TargetClass {String targetField;}
public class SourceClass<T> {static class SourceStaticNested {}
public void example() {class LocalInner {}}
class SourceInner {@CustomAnnotation // has_annotationprivate Object methodToMove(TargetClass target) {// Variable call + Access instance fieldString var = target.targetField;target.targetField = "updated";
// Uses outer thisSourceClass<T> outer = SourceClass.this;SourceStaticNested nested = new SourceStaticNested();
// Switch caseswitch (var != null ? var.length() : 0) {case 3:var = var.toUpperCase();break;default:var = "default";}
return var;}
// Override violation: overriding final method (Object's final method)@Overridepublic final String toString() {return super.toString();}}}