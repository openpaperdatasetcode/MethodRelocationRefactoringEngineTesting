package test;
import java.util.List;import java.util.ArrayList;
class ParentTarget {protected String superField = "superFieldValue";}
non-sealed class TargetClass extends ParentTarget {String targetField = "targetFieldValue";
void methodWithLocal() {class LocalInner {}}
public <T> List<String> genericMethod(T param) {return new ArrayList<>();}}
protected class SourceClass {/**
Javadoc for varargsMethod*/private TargetClass varargsMethod(TargetClass... targets) {if (targets == null) {return null;}
class DiffPackageOthers {private String var = targets[0].superField;}new DiffPackageOthers();
TypeDeclared typeDecl = new TypeDeclared();
String null1 = null;Object null2 = null;
TargetClass result = null;int i = 0;do {try {List<String> list = targets[i].<Integer>genericMethod(1);result = targets[i];i++;} catch (Exception e) {break;}} while (i < targets.length);
return result;}
void anonymousClasses() {Runnable r1 = new Runnable() {public void run() {}};Runnable r2 = new Runnable() {public void run() {}};}}
class TypeDeclared {}