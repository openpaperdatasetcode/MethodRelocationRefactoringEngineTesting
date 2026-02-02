package samepkg;
import java.util.List;import java.util.ArrayList;import java.util.Collection;
interface ParentInterface {}
interface SourceInterface<T extends Number> extends ParentInterface {static class SourceStaticNested {}
default List<String> overloadedMethod() {return new ArrayList<>();}
default List<String> overloadedMethod(StrictfpTargetInterface targetParam) {do {class LocalTypeDeclaration {}LocalTypeDeclaration localType = new LocalTypeDeclaration();
StrictfpTargetInterface varCall = targetParam;Collection rawCollection = new ArrayList();rawCollection.add(varCall.new TargetInner());} while (targetParam != null);
return new ArrayList<>();}
default void methodWithLocal() {class LocalInner {}new LocalInner();}}
strictfp interface StrictfpTargetInterface {class TargetInner {}}