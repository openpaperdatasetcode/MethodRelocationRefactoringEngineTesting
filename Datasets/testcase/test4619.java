package test;
import java.util.List;import java.util.ArrayList;
interface ParentInterface {}
@interface SourceInterface {class InnerOne {}class InnerTwo {}
/**
Varargs method javadoc
*/
private List<String> varargsMethod(TargetInterface... targets) {
Label: {
for (TargetInterface target : targets) {
super.toString();
InnerOne one = new InnerOne();
if (target == null) break Label;
}
}
return new ArrayList<>();
}
}
@interface TargetInterface extends ParentInterface {default void hasLocalInner() {class LocalInner {}new LocalInner();}}
