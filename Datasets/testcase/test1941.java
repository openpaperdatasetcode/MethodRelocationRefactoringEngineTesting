package test;
protected record SourceRecord(String data) {class MemberInner {// Overloading methods with no type parametersvoid process(TargetRecord target) {// Variable call to target's componentString value = target.value();System.out.println("Processing: " + value);
// Local inner classclass LocalProcessor {void handle() {target.action().run();}}new LocalProcessor().handle();}
void process(TargetRecord target, int count) {// Variable call to target's inner logicfor (int i = 0; i < count; i++) {System.out.println(target.value() + " - " + i);}}}}
record TargetRecord(String value) {// Anonymous inner class in target recordRunnable action() {return new Runnable() {@Overridepublic void run() {System.out.println("Action on: " + value);}};}}