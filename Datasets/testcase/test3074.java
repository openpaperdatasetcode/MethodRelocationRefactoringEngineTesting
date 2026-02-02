package test;
import java.sql.SQLException;
final class TargetClass<T> {T targetField;
public void example() {class LocalInner {}}}
class SourceClass {private int outerPrivateField = 10;
int methodToMove(TargetClass target, String... varargs) throws SQLException { // Static ForStatement with obj.field and value 2 static class ForLoopProcessor { static void process(TargetClass target) {for (int i = 0; i < 3; i++) {Object val = target.targetField;target.targetField = (Object) 2;}}}ForLoopProcessor.process(target);
// ArrayInitializer with number 1String[] arr = { "1" };
// Variable call + Access outer private fieldint var = outerPrivateField;target.targetField = var;
// Expression statementvar++;
return var;}}