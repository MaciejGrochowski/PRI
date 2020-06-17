import styled from "styled-components";
import CloseIcon from "@material-ui/icons/Close";
import React from "react";

export const Tag = styled(({ label, onDelete, id, ...props }) => (
    <>
        {label && label.map((item, i) => (
            <div {...props}>
                <div className={id}>{item}</div>
                <CloseIcon onClick={() => onDelete(item)}/>
            </div>
        ))}
    </>
))`
overflow: hidden;
text-overflow: ellipsis;
white-space: nowrap;
  display: flex;
  align-items: center;
  // height: 24px;
  color: #FFD859;
  // line-height: 22px;
  background-color: #242a2a;
  border: 1px solid #FFD859;
  border-radius: 20px;
  box-sizing: content-box;
  // padding: 0 4px 0 10px;
  margin: 3px;
  padding-left: 5px;
  outline: 0;

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
    margin: 4px;
  }
`;