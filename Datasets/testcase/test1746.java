package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface Loggable {}
protected class Source {class SourceInner {@Loggablevoid process(Target<String> target, String... args) {TargetInner inner = target.new TargetInner();switch (args.length) {case 0:break;case 1:inner.setValue(args[0]);break;default:inner.setValue(target.prefix + args[0]);}}
void process(int num) {}}
Runnable anonymous = new Runnable() {public void run() {new SourceInner().process(new Target<>(), "test");}};}
/**
Target class with type parameter and inner class*/public class Target<T> {String prefix;
class TargetInner {private String value;
public void setValue(String val) {this.value = val;}}}