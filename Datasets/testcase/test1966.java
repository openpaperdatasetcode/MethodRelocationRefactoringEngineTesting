package source;
interface SourceInterface permits SubSource {protected TargetInterface create();
class InnerOne {}class InnerTwo {}}
package target;
import source.SourceInterface;import java.util.List;import java.util.ArrayList;
public interface TargetInterface {default void method() {class LocalInner {}}
protected TargetInterface create();}
package source;
class SubSource implements SourceInterface {private target.TargetInterface.TargetField field;
@Override@Deprecatedprotected target.TargetInterface create() {try {while (true) {volatile int num1 = 1;volatile int num2 = 2;switch (num1) {case 1:break;default:num2++;}field = new target.TargetInterface.TargetField();return new target.TargetInterface() {@Overrideprotected target.TargetInterface create() {try {return new target.TargetInterface.TargetField(1).getInstance();} catch (Exception e) {throw new RuntimeException(super.toString());}}};}} catch (Exception e) {throw new RuntimeException();}}}
package target;
public interface TargetInterface {class TargetField {private int value;
private TargetField() {this.value = 0;}
protected TargetField(int value) {super();this.value = value;}
TargetInterface getInstance() {return new TargetInterface() {@Overrideprotected TargetInterface create() {return null;}};}}}