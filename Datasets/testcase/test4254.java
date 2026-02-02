package test;
import java.io.IOException;
private sealed class SourceClass permits SourceSubclass {protected int outerProtectedField;private TargetClass targetField = new TargetClass();
static class SourceStaticNested {}
void createLocalInner() {class SourceLocalInner {}new SourceLocalInner();}
class SourceInner {default int instanceMethod() throws IOException {Labeled: {TypeDeclaration typeDecl = new TypeDeclaration();TargetClass.InnerRecursive innerRec = targetField.new InnerRecursive();
int count = 0;do {count += this.field;if (count > 5) break Labeled;} while (count < 10);
innerRec.recursiveMethod(3);return targetField.innerField + outerProtectedField;}}
private int field;}}
final class SourceSubclass extends SourceClass {}
class TargetClass {int innerField;
class InnerRecursive {public Object recursiveMethod(int depth) {if (depth <= 0) return new Object();Runnable lambda = () -> recursiveMethod(depth - 1);lambda.run();return recursiveMethod(depth - 1);}}}
class TypeDeclaration {}