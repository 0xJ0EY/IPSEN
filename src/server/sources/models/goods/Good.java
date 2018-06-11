package server.sources.models.goods;

import java.io.Serializable;

public interface Good extends Serializable {

    public String getBackground();

    public boolean sameInstance(Good good);

}
