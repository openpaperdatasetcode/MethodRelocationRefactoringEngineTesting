import java.util.Objects;
// Parent class for TargetEnum to support 'extends' featureclass ParentEnumBase {protected char superField1 = 'A';protected char superField2 = 'B';}
// TargetClass: enum, protected, extends ParentEnumBase, with anonymous inner class (meets target_class specs)protected enum TargetEnum extends ParentEnumBase {VALUE1, VALUE2;
// Anonymous inner class (target_feature)private final Runnable initRunner = new Runnable() {@Overridepublic void run() {// Access super fields from ParentEnumBaseSystem.out.println("Target init: " + superField1 + superField2);}};
public void executeInit() {initRunner.run();}
public void updateSuperFields(char c1, char c2) {this.superField1 = c1;this.superField2 = c2;}
// Static field for 'depends_on_static_field' featurepublic static final String TARGET_STATIC = "TARGET_STATIC_DATA";}
// SourceClass: enum, private, same package as target, with member inner class + anonymous inner class (meets source_class specs)private enum SourceEnum {INSTANCE;
// Private outer field for 'access_outer_private' featureprivate String outerPrivateField = "source_private_data";
// Member inner class (source_feature)protected class SourceInnerClass {// Recursion method: meets method specs (recursion, void, default access, source_inner)void recursiveProcess(TargetEnum target, int depth) {if (depth <= 0) {return;}
// Type declaration statement (method_feature)TargetEnum.LocalProcessor processor = target.new LocalProcessor();
// WhileStatement: meets method_feature (public modifier, pos=source, access 2 super fields)int count = 0;while (count < 2) {// Expression statement (method_feature)target.updateSuperFields('X', 'Y');// Access 2 super fields of target (target_feature: super.field 2)System.out.println("While loop: " + target.superField1 + target.superField2);count++;}
// 2 CharacterLiteral (method_feature: numbers=2, protected modifier)char charLit1 = 'M';char charLit2 = 'N';// Variable call (method_feature)target.updateSuperFields(charLit1, charLit2);processor.process(charLit1, charLit2);
// Access outer private field (access_outer_private feature)System.out.println("Outer private: " + SourceEnum.this.outerPrivateField);// Depend on target's static field (depends_on_static_field feature)System.out.println("Target static: " + TargetEnum.TARGET_STATIC);
// Recursion callrecursiveProcess(target, depth - 1);}}
// Anonymous inner class (source_feature)private final SourceInnerClass innerInstance = new SourceInnerClass() {public void triggerProcess(TargetEnum target) {recursiveProcess(target, 2); // Initiate recursion with depth=2}};
public void startProcessing(TargetEnum target) {// Ensure method contains target parameter (per_condition)Objects.requireNonNull(target, "Target cannot be null");innerInstance.triggerProcess(target);}}
// Nested class in TargetEnum for 'type declaration statement' usageinterface TargetEnum.LocalProcessor {void process(char c1, char c2);}
// Concrete implementation of TargetEnum.LocalProcessorclass TargetProcessor implements TargetEnum.LocalProcessor {@Overridepublic void process(char c1, char c2) {System.out.println("Process chars: " + c1 + c2);}}
// Test entry to trigger the flow (no_new_exception)public class EnumRefactorTest {public static void main(String[] args) {SourceEnum.INSTANCE.startProcessing(TargetEnum.VALUE1);}}