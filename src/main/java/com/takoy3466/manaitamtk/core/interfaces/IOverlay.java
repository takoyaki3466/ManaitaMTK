package com.takoy3466.manaitamtk.core.interfaces;

import com.takoy3466.manaitamtk.core.MTKOverlayIcon;

import java.util.List;

public interface IOverlay {

    void setIconList(List<MTKOverlayIcon> iconList);

    List<MTKOverlayIcon> getIconList();
}
