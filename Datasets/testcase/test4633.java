package test;
import java.util.List;import java.util.ArrayList;
record SourceClass(int id) {static class StaticNestedSource {}
class InnerSource {strictfp List<String> varargsMethod(TargetClass... targets) {super.toString();List<String> result = new ArrayList<>();int count = 0;
while (count < 1) {for (TargetClass target : targets) {result.add(String.valueOf(target.value()));result.add(target.instanceMethod());if (target.value() > 0) {count++;}}}
class LocalInner {}new LocalInner();return result;}
@Overridepublic String toString() {return "";}}}
public record TargetClass(int value) {static class StaticNestedTarget {}
public String instanceMethod() {return String.valueOf(value);}}