\*Cat-drawing-tool 
Processing 3 project
A cat that follows a laser pointer around. Click and drag the mouse to move the laser pointer.
*/

boolean held;
Float dirX = mouseX - width/24.0;
Float dirY = mouseY - (sqrt(3)*(width/24));
int num = 50;
float mx[] = new float[num];
float my[] = new float[num];
float nx[] = new float[num];
float ny[] = new float[num];
Cat cat = new Cat(300,720);

void setup() {
    size(800, 800);
    smooth();
    background(244,144,4); 
    frameRate(60);
    noCursor();
}

void laser() {
  held = true;
}

void draw() {
  background(244,144,4);
  stroke(0);
  strokeWeight(10);
  line(mouseX, mouseY, mouseX + width/24, mouseY + (sqrt(3)*(width/24)));
  
  dirX = mouseX - width/24.0;
  dirY = mouseY - (sqrt(3)*(width/24));
  
  for(int i=1; i<num; i++) {
    mx[i-1] = mx[i];
    my[i-1] = my[i];
    nx[i-1] = nx[i];
    ny[i-1] = ny[i];
  } 
  
  for(int i = 0; i < num; i++){
      noStroke();
      fill(221,96,6);
      ellipse(mx[i],my[i],10,10);
      
      noStroke();
      fill(221,96,6);
      ellipse(nx[i],ny[i],10,10);
    }
  
  if(held) {
    noStroke();
    fill(255,17,5);
    ellipse(mouseX,mouseY,7,7);
    noStroke();
    fill(255,17,5);
    ellipse(mouseX - width/24,mouseY - (sqrt(3)*(width/24)), 7,7);
    cat.update();
    cat.moveDisplay();
    
  } else {
    background(244,144,4);
    stroke(0);
    strokeWeight(10);
    line(mouseX, mouseY, mouseX + width/24, mouseY + (sqrt(3)*(width/24)));
  
    dirX = mouseX - width/24.0;
    dirY = mouseY - (sqrt(3)*(width/24));
    cat.sitDisplay();
  }
}

void mouseDragged() {
  mx[num - 1] = cat.getX() - 10;
  my[num - 1] = cat.getY();
  
  nx[num - 1] = cat.getX() + 10;
  ny[num - 1] = cat.getY() + 20;
  laser();
}

void mouseReleased() {
  held = false;
}

void keyPressed(){
  if (key == 32){
    cat = new Cat(300,720);
    setup();
  }
}

class Cat {
  PVector position;
  PVector speed = new PVector(1,1);
  PVector acceleration;

  
  Cat(float x, float y) {
    position = new PVector(x, y);
  }
  
  float getX(){
    return position.x;
  }
  
  float getY(){
    return position.y;
  }
  

  
  void update() {
    PVector place = new PVector(dirX,dirY);
    place.sub(position);
    place.setMag(2);
    acceleration = place;
    speed.add(acceleration);
    position.add(speed);
    speed.limit(5);
  }
  
  void sitDisplay() {
    noStroke();
    fill(0);
    ellipse(position.x,position.y-50,50,50);
    
    noStroke();
    fill(0);
    triangle(position.x-25,position.y-50,position.x-10,position.y-65,
    position.x-25,position.y-70);
    
    noStroke();
    fill(0);
    triangle(position.x+25,position.y-50,position.x+10,position.y-65,
    position.x+25,position.y-70);
    
    noStroke();
    fill(0);
    ellipse(position.x,position.y,50,80);
    
    stroke(0);
    noFill();
    strokeWeight(7);
    bezier(position.x, position.y+36, position.x+70, position.y+10,
    position.x+50, position.y-80, position.x+30, position.y-30);
    
  }
  
  void moveDisplay() {
    noStroke();
    fill(0);
    ellipse(position.x,position.y-50,50,50);
    
    noStroke();
    fill(0);
    triangle(position.x-25,position.y-50,position.x-10,position.y-65,
    position.x-25,position.y-70);
    
    noStroke();
    fill(0);
    triangle(position.x+25,position.y-50,position.x+10,position.y-65,
    position.x+25,position.y-70);
    
    noStroke();
    fill(0);
    ellipse(position.x,position.y,50,80);
    
    stroke(0);
    noFill();
    strokeWeight(7);
    bezier(position.x, position.y+36, position.x-40, position.y+66,
    position.x+50, position.y+96, position.x, position.y+126);
    
  }
}
