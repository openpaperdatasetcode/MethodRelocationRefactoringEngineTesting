package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface Logging {}
@Retention(RetentionPolicy.RUNTIME)@interface Validated {}
private class Source<T> {class MemberInner {T content;}
@Logging@Validatedprivate void process(Target target, String... messages) {Target.InnerClass inner = target.new InnerClass();String name = target.name;int age = target.age;
for (int i = 0; i < messages.length; i++) {inner.setData(messages[i]);if (i % 2 == 0) {inner.print();}}
class LocalHandler {void update(Target t) {t.name = "updated";t.age++;}}new LocalHandler().update(target);}}
class Target {String name;int age;
class InnerClass {private String data;
void setData(String data) {this.data = data;}
void print() {System.out.println(data);}}}