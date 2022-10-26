package GameDemo;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    int[][] data = new int[4][4];
    int x;
    int y;
    int[][] win ={
            {1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}
    };
    int count = 0;
    String imageType = "animal";//默认选择动物图片
    int imageNo = 1;//默认1图片号码
    Random random = new Random();

    //创建菜单选项下的条目对象
    JMenu changePictureItem = new JMenu("更换图片");//JMenu才能放JMenuItem
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem girlItem = new JMenuItem("美女");
    JMenuItem sportItem = new JMenuItem("运动");

    JMenuItem helpItem = new JMenuItem("帮助");

    JMenuItem replayItem = new JMenuItem("重新游戏");
//    JMenuItem reLoglinItem = new JMenuItem("重新登录");
    JMenuItem exitItem = new JMenuItem("关闭游戏");

    JMenuItem acountItem = new JMenuItem("制作成员");
    //构造方法初始化
    public  GameJFrame(){
        //初始化窗口
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //打乱图片
        randomImage();

        //加载图片
        initImage();


        //显示窗口

        this.setVisible(true);
    }

    private void randomImage() {
        //初始化数组
        int[] temp = new int[16];
        for (int i = 0; i < 16; i++) {
            temp[i] = i;
        }
        //打乱数组

        for (int i = 0; i < temp.length; i++) {
            int no1 = random.nextInt(temp.length);
            if(no1 != i){
                int a = temp[no1];
                temp[no1] = temp[i];
                temp[i] = a;
            }
        }
        //将一维数组转化为二维数组
        for(int i = 0;i < temp.length;i++){
            if(temp[i] == 0){
                x = i / 4;
                y = i % 4;
            }
            data[i/4][i%4] = temp[i];
        }
    }

    private void initImage() {


/*        //创建图片对象
        ImageIcon icon = new ImageIcon("D:\\Java_SlackerC\\itheima\\exercise\\image\\animal\\animal3\\1.jpg");
        //创建JLable对象（容器）
        JLabel jLabel = new JLabel(icon);
        //指定图片位置
        jLabel.setBounds(0,0,105,105);
        //添加到隐藏容器（JFrame默认创建一个隐藏容器）
        this.getContentPane().add(jLabel);*/


        /*for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                String no = String.valueOf(i+1+j*4);
                JLabel jLabel = new JLabel(new ImageIcon("D:\\Java_SlackerC\\itheima\\exercise\\image\\animal\\animal3\\" + no + ".jpg"));
                //指定图片位置
                jLabel.setBounds(105*i,105*j,105,105);
                //添加到隐藏容器（JFrame默认创建一个隐藏容器）
                this.getContentPane().add(jLabel);
            }
        }*/
        //随机选择第imageNo张照片


        this.getContentPane().removeAll();

        if(victory()){
            JLabel jLabel = new JLabel(new ImageIcon("image\\win.png"));
            jLabel.setBounds(203,283,197,73);
            this.getContentPane().add(jLabel);
        }

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                JLabel jLabel = new JLabel(new ImageIcon("image\\" + imageType + "\\" + imageType + imageNo + "\\" + data[j][i] + ".jpg"));
                //指定图片位置
                jLabel.setBounds(105*i+83,105*j+134,105,105);
                //设置JLabel边框
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                //添加到隐藏容器（JFrame默认创建一个隐藏容器）
                this.getContentPane().add(jLabel);
            }
        }

        JLabel backGround = new JLabel(new ImageIcon("image\\background.png"));
        backGround.setBounds(40,40,508,560);
        this.getContentPane().add(backGround);

        JLabel countlabel = new JLabel("步数：" + count);
        countlabel.setBounds(50,15,100,20);
        this.getContentPane().add(countlabel);
        this.repaint();

    }

    private boolean victory() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(data[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    private void initJMenuBar() {
        //创建菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上选项对象
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutMeJMenu = new JMenu("关于作者");



        //将条目对象添加到菜单选项中
        changePictureItem.add(animalItem);
        changePictureItem.add(girlItem);
        changePictureItem.add(sportItem);
        functionJMenu.add(changePictureItem);

        functionJMenu.add(helpItem);

        functionJMenu.add(replayItem);
//        functionJMenu.add(reLoglinItem);
        functionJMenu.add(exitItem);

        aboutMeJMenu.add(acountItem);

        //绑定事件
        changePictureItem.addActionListener(this);
        animalItem.addActionListener(this);
        girlItem.addActionListener(this);
        sportItem.addActionListener(this);
        helpItem.addActionListener(this);
        replayItem.addActionListener(this);
//        reLoglinItem.addActionListener(this);
        exitItem.addActionListener(this);
        acountItem.addActionListener(this);

        //将菜单选项添加到菜单
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutMeJMenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        this.setSize(603,680);
        //设置标题
        this.setTitle("拼图小游戏 V1.0");
        //设置窗口居中
        this.setLocationRelativeTo(null);
        //设置窗口置顶
        this.setAlwaysOnTop(true);
        //取消默认居中
        this.setLayout(null);
        //设置关闭方式
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //添加键盘监听
        this.addKeyListener(this);

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(victory())return;
        if(e.getKeyCode() == 32){
            this.getContentPane().removeAll();

            JLabel all = new JLabel(new ImageIcon("image\\" + imageType + "\\" + imageType + imageNo + "\\all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);

            JLabel backGround = new JLabel(new ImageIcon("image\\background.png"));
            backGround.setBounds(40,40,508,560);
            this.getContentPane().add(backGround);

            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(victory())return;

        int code = e.getKeyCode();
        if (code == 37) {//左
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            count++;
        } else if (code == 38) {//上
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            count++;
        } else if (code == 39) {//右
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            count++;
        } else if (code == 40) {//下
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            count++;
        }else if (code == 32){//空格
        }//啥也不做
        else if(code == 18){//Alt
            for (int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++) {
                    data[i][j] = win[i][j];
                }
            }
        }
        initImage();
    }

    @Override
    //重写actionPerformed方法，实现点击菜单条目功能
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的条目对象
        Object obj = e.getSource();
         if(obj == replayItem){
            randomImage();
            count = 0;
            initImage();
//        }else if(obj == reLoglinItem){
//            this.setVisible(false);
//            new LoginJFrame();
        }else if(obj == exitItem){
            System.exit(0);
        }else if(obj == acountItem){
            JDialog jDialog = new JDialog();
            JLabel aboutme = new JLabel("<html>20级数据科学与大数据技术<br>032004107陈骏灵<br>032004102蔡勇捷</html>",SwingConstants.CENTER);
            aboutme.setBounds(0,0,208,158);
            jDialog.getContentPane().add(aboutme);
            jDialog.setSize(300,200);
            jDialog.setLocationRelativeTo(null);
            jDialog.setAlwaysOnTop(true);
            //弹窗不关闭则无法操作下面窗口
            jDialog.setModal(true);
            jDialog.setVisible(true);
        }else if(obj == animalItem){
            imageType = "animal";
            imageNo = random.nextInt(8)+1;
            randomImage();
            initImage();
        }else if(obj == girlItem) {
            imageType = "girl";
            imageNo = random.nextInt(13)+1;
            randomImage();
            initImage();

        }else if(obj == sportItem){
            imageType = "sport";
            imageNo = random.nextInt(10)+1;
            randomImage();
            initImage();

        }else if(obj == helpItem){
             JDialog jDialog = new JDialog();
             JLabel help = new JLabel("<html>1、上下左右移动照片<br>2、空格键查看完整照片<br>3、Alt键一键复原！</html>",SwingConstants.CENTER);
             help.setBounds(0,0,208,158);
             jDialog.getContentPane().add(help);
             jDialog.setSize(300,200);
             jDialog.setLocationRelativeTo(null);
             jDialog.setAlwaysOnTop(true);
             //弹窗不关闭则无法操作下面窗口
             jDialog.setModal(true);
             jDialog.setVisible(true);
         }
    }
}
