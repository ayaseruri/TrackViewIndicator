package andy.ayaseruri.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by ayaseruri on 15/8/24.
 */
public class TabView extends FrameLayout {

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    private int direction = LEFT;
    private int mIndex;

    private Bitmap tabItemBitOrg;
    private Bitmap tabItemBitAft;

    private boolean isGetOrgBitmap = true;
    private float progress;

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabView(Context context, IGetSnapshot iGetSnapshot) {
        super(context);
        init(iGetSnapshot);
    }

    private void init(IGetSnapshot iGetSnapshot){

        this.addView(iGetSnapshot.getOrgSnapshot(), new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        View orgView = iGetSnapshot.getOrgSnapshot();
        tabItemBitOrg = view2Bitmap(orgView);
        iGetSnapshot.getAftSnapshot(orgView);
        tabItemBitAft = view2Bitmap(orgView);
        isGetOrgBitmap = false;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if(!isGetOrgBitmap){
            if(direction == LEFT){
                drawBit(canvas, tabItemBitAft, 0, (int)(tabItemBitAft.getWidth() * progress));
            }else if(direction == RIGHT){
                drawBit(canvas, tabItemBitAft, (int) (tabItemBitAft.getWidth() * (1 - progress)), tabItemBitAft.getWidth());
            }
        }
    }

    private Bitmap view2Bitmap(View v){
        v.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);;
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        Bitmap b = Bitmap.createBitmap(v.getMeasuredWidth(), v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);
        return b;
    }

    private void drawBit(Canvas canvas, Bitmap bitmap, int startX, int endX){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect((getMeasuredWidth() - bitmap.getWidth())/2 + startX, 0, (getMeasuredWidth() - bitmap.getWidth())/2 +  endX, bitmap.getHeight());
        canvas.drawBitmap(bitmap, (getMeasuredWidth() - bitmap.getWidth())/2, 0, paint);
        canvas.restore();
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int mIndex) {
        this.mIndex = mIndex;
    }

    public void setProgress(float progress) {
        if(this.progress != progress){
            this.progress = progress;
            postInvalidate();
        }
    }

    public interface IGetSnapshot{
        View getOrgSnapshot();
        void getAftSnapshot(View orgView);
    }
}
