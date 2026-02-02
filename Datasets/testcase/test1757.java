package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface Config {String value() default "";}
class Source {static class Nested1 {static String data = "nested1";}
static class Nested2 {static int count = 0;}
static {Target target = new Target();Target.StaticNested nested = new Target.StaticNested();getTargetData(target, nested);}
public static Target getTargetData(Target target, Target.StaticNested nested) {target.setValue(nested.getInfo());return target;}
/**
Processes target with variable arguments
@param target the target instance
@param args variable arguments
@return processed object*/@Config(value = "${process}")protected <T extends CharSequence> Object process(Target target, T... args) {Target.StaticNested nested = new Target.StaticNested();nested.setId(10);target.setNested(nested);
for (T arg : args) {target.handle(arg);}
return target.getNested();}}
class Target {private StaticNested nested;
static class StaticNested {private int id;private String info;
int getId() {return id;}
void setId(int id) {this.id = id;}
String getInfo() {return info;}
void setInfo(String info) {this.info = info;}}
private void handle(CharSequence str) {nested.setInfo(str.toString());}
void setNested(StaticNested nested) {this.nested = nested;}
StaticNested getNested() {return nested;}
void setValue(String val) {}}