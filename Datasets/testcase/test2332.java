package same.pkg;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
private enum Source {INSTANCE;
class MemberInner {}
void methodWithLocal() {class LocalInner {}}
@MyAnnotationprivate Target varargsMethod(Target... targetParams) {// Labeled statementprocess: {if (targetParams.length == 0) {break process;}}
// VariableDeclarationExpression with numbers=1public int count = 1;
// Super keywordssuper.toString();
// Variable callTarget target = targetParams[0];Target.StaticNested nested = Target.StaticNested.INSTANCE;
// Depends on inner classTarget result = nested.createTarget();
return result;}}
public enum Target implements Runnable {INSTANCE;
int field;
static class StaticNested {static final StaticNested INSTANCE = new StaticNested();
Target createTarget() {return Target.INSTANCE;}}
@Overridepublic void run() {}}
