package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnno {}
enum SourceEnum {INSTANCE;
private TargetEnum targetField = TargetEnum.VALUE;
class MemberInner {}
final Object method() {new Object() {};return targetField;}
@TestAnnofinal <T> void method(T param) {switch (param) {case Integer i -> super.toString();default -> {}}}
protected Object method(int[] arr) {return arr[0];}}
final enum TargetEnum {VALUE;}
strictfp class ParentClass {void callMethod() {Object[] arr = {SourceEnum.INSTANCE.method(),(Integer i) -> SourceEnum.INSTANCE.method(i),(int[] a) -> SourceEnum.INSTANCE.method(a)};}}