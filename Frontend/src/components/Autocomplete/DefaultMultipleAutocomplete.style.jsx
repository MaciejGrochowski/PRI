import styled from "styled-components";
import CloseIcon from "@material-ui/icons/Close";
import React from "react";

export const Tag = styled(({ label, onDelete, id, ...props }) => (
    <>
        {label && label.map((item, i) => (
            <div {...props}>
                <span className={id}>{item}</span>
                <CloseIcon onClick={() => onDelete(item)}/>
            </div>
        ))}
    </>
))`
  display: flex;
  align-items: center;
  height: 24px;
  color: #FFD859;
  line-height: 22px;
  background-color: #292F2F;
  border: 1px solid #FFD859;
  border-radius: 2px;
  box-sizing: content-box;
  padding: 0 4px 0 10px;
  margin-left: 5px;
  outline: 0;
  overflow: hidden;

  &:focus {
    background-color: #292F2F;
    border: 1px solid #FFD859;
    color: #FFD859;
  }

  & span {
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }

  & svg {
    font-size: 12px;
    cursor: pointer;
    padding: 4px;
  }
`;