package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnn {}
non-sealed enum SourceEnum {INSTANCE;
static class StaticNested {}
void methodWithLocal() {class LocalInner {}}
class SourceInner {@MyAnnprotected List<String> varargsMethod(TargetEnum<? extends CharSequence>... targets) {variableCall = targets[0].field;TargetEnum.StaticNested::new;
return new ArrayList<>();}
String variableCall;}}
enum TargetEnum<T> {VALUE;
String field;static class StaticNested {}}